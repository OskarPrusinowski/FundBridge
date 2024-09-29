import { createRouter, createWebHistory } from 'vue-router'
import Home from './views/Home.vue'
import Login from './views/Login.vue'
import Registration from './views/Registration.vue'
import TinderToNawetNieJestOn from './views/TinderToNawetNieJestOn.vue'
import Foundation from './views/Foundation.vue'

import FoundationsList from './views/FoundationsListPage.vue'
const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home,
    },
    {
        path: '/foundations',
        name: 'FoundationsList',
        component: FoundationsList
    },
    {
        path: '/login',
        name: 'Login',
        component: Login,
    },
    {
        path: '/registration',
        name: 'registration',
        component: Registration,
    },
    {
        path: '/match',
        name: 'Match',
        component: TinderToNawetNieJestOn,
    },
    {
        path: '/foundations/:id',
        name: 'Foundation',
        component: Foundation,
    },
]

const router = createRouter({
    history: createWebHistory(),
    routes,
})

export default router
