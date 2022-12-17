import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    name: 'home',
    meta: { title: 'Homepage' },
    component: () => import(/* webpackChunkName: "home" */ '@/views/Homepage.vue')
  },
  {
    path: '/results',
    name: 'results',
    meta: { title: 'View results' },
    component: () => import(/* webpackChunkName: "search" */ '@/views/ResultsPage.vue')
  },
  {
    path: '/quiz',
    name: 'quiz',
    meta: { title: 'Quiz' },
    component: () => import(/* webpackChunkName: "quiz" */ '@/views/QuizPage.vue')
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
