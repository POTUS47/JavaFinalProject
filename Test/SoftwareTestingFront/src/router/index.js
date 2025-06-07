import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    component: () => import('../views/Layout.vue'),
    children: [
      {
        path: '',
        redirect: '/exercise/triangleJudge'
      },
      {
        path: 'exercise/:id',
        component: () => import('../views/ExerciseTest.vue'),
        props: true
      },
      {
        path: 'project/unit',
        component: () => import('../views/ProjectUnitTest.vue'),
        props: true
      },
      {
        path: 'project/integration',
        component: () => import('../views/ProjectIntegrationTest.vue'),
        props: true
      },
      {
        path: 'project/system',
        component: () => import('../views/ProjecSystemtTest.vue'),
        props: true
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
