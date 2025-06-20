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
        component: () => import('../views/ExerciseTest.vue')
      },
      {
        path: 'project/unit',
        component: () => import('../views/ProjectUnitTest.vue')
      },
      {
        path: 'project/integration',
        component: () => import('../views/ProjectIntegrationTest.vue')
      },
      {
        path: 'project/integration/:id',
        component: () => import('../views/IntegrationTestView.vue')
      },
      {
        path: 'project/system',
        component: () => import('../views/ProjectSystemTest.vue')
      },
      {
        path: 'project/test',
        component: () => import('../views/testVue.vue')
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
