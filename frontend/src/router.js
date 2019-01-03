import Vue from "vue";
import Router from "vue-router";
import Home from "./views/Home.vue";
import Weather from "./components/Weather.vue";


Vue.use(Router);

export default new Router({
    routes: [
        {
            path: "/",
            name: "home",
            component: Home
        },
        {
            path: '/weather',
            name: 'Weather',
            component: Weather
        }
    ]
});
