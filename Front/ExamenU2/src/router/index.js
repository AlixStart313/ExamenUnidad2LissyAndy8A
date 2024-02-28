import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter)

const router = new VueRouter({
    mode: 'history',
    base: import.meta.env.BASE_URL,
    routes: [
        {
            path:"/",
            name: "inicio",
            component:() => import('../components/Inicio.vue')
        },
        {
            path:"/inicio",
            name: "inicio",
            component:() => import('../components/Inicio.vue')
        },
        {
            path:"/BookCard",
            name: "BookCard",
            component:() => import('../components/BookCard.vue')
        },

    ]
})

export default router