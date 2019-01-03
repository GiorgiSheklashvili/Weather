<template>
    <div class="weather">
        <div class="generator-alert">
            <b-alert v-if="errorMessage" variant="danger" :show="errMessageShowDuration">{{errorMessage}}</b-alert>
        </div>
        <h1>Search by city(Harku, Jõhvi, Tartu, Pärnu, Kuressaare, Türi)</h1>

        <input type="text" v-model="cityName" placeholder="city name">

        <b-button @click="searchByCity()">Search</b-button>

        <div v-if="showResponse">
            <p>{{response[0].name}}</p>
            <p>Day: max temperature: {{ response[0].tempmax }}
                Phenomenon: {{ response[0].phenomenon }}
            </p>
            <p>Night: min temperature: {{ response[1].tempmin }}
                Phenomenon: {{ response[1].phenomenon }}
            </p>
        </div>
        <br>
        <br>
        <b-button @click="retrieveLatestWeather()">Retrieve latest weather forecast</b-button>

        <b-row v-if="latestWeather.forecastDtos && latestWeather.forecastDtos.length > 0">
            <b-col>
                <b-table id="weatherTable"
                         responsive="sm"
                         striped hover
                         :fields="fields"
                         :items="latestWeather.forecastDtos">
                    <template slot="date" slot-scope="data">{{ data.item.date }}</template>
                    <template slot="night" slot-scope="data">
                        {{ data.item.night.phenomenon}}
                        Max: {{ data.item.night.tempmax }}
                        Min: {{ data.item.night.tempmin }}
                        <br>
                        Description: {{data.item.night.text}}
                    </template>
                    <template slot="day" slot-scope="data">
                        {{ data.item.night.phenomenon }}
                        Max: {{ data.item.night.tempmax }}
                        Min: {{ data.item.night.tempmin }}
                        <br>
                        Description: {{data.item.night.text}}
                    </template>
                    <template  slot="place" slot-scope="data">
                        <b-btn v-if="data.item.night.places.length!=0" v-b-toggle.collapse1>NightTime</b-btn>
                        <b-collapse id="collapse1" class="mt-2">
                        <ul>
                            <li v-for="place in data.item.night.places" v-bind:key="place.id">
                                {{place.name}} {{place.tempmin}}
                            </li>
                        </ul>
                        </b-collapse>
                        <b-btn v-if="data.item.day.places.length!=0" v-b-toggle.collapse2>DayTime</b-btn>
                        <b-collapse id="collapse2" class="mt-2">
                        <ul>
                            <li v-for="place in data.item.day.places" v-bind:key="place.id">
                                {{place.name}} {{place.tempmax}}
                            </li>
                        </ul>
                        </b-collapse>
                    </template>
                </b-table>
            </b-col>
        </b-row>

    </div>
</template>

<script>
    import {AXIOS} from './http-common'

    export default {
        name: 'Weather',

        data() {
            return {
                cityName: "",
                response: [],
                showResponse: false,
                latestWeather: {},
                errMessageShowDuration: 10,
                errorMessage: undefined,
                fields: [{key: 'date', label: 'Date'}, {key: 'night', label: 'Night'}, {
                    key: 'day',
                    label: 'day'
                }, {key: 'place', label: 'Place'}]
            }
        },
        methods: {
            // Fetches posts when the component is created.
            searchByCity() {
                AXIOS.get(`/places`, {
                    params: {
                        name: this.cityName
                    }
                })
                    .then(response => {
                        this.response = response.data;
                        this.showResponse = true;
                    })
                    .catch(e => {
                        this.errorMessage = "Name not found";
                    })
            },
            retrieveLatestWeather() {
                AXIOS.get(`/forecast/latest`)
                    .then(response => {
                        this.latestWeather = response.data;
                    })
                    .catch(e => {
                        this.errorMessage = "Couldn't get Latest weather forecast";
                    })
            }
        }
    }

</script>