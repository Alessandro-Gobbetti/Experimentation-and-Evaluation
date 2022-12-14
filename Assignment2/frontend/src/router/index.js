import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  // {
  //   path: '/Experimentation-and-Evaluation/',
  //   name: 'home',
  //   meta: { title: 'Homepage' },
  //   component: () => import(/* webpackChunkName: "home" */ '@/views/Homepage.vue')
  // },
  {
    path: '/Experimentation-and-Evaluation/',
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
