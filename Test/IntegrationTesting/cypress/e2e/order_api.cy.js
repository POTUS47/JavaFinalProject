// cypress/e2e/order_api.cy.js
describe('订单管理接口测试', () => {
    beforeEach(() => {
        cy.login()
    })

    it('获取买家所有订单 - GET /order/get-all-buyer-orders', () => {
        cy.request({
            method: 'GET',
            url: `${Cypress.env('apiUrl')}/order/get-all-buyer-orders`,
            headers: cy.getAuthHeader()
        }).then((response) => {
            expect(response.status).to.eq(200)
            expect(response.body).to.have.property('code', 200)
            expect(response.body.data).to.be.an('array')

            // 如果有订单，可以测试获取单个订单
            if (response.body.data.length > 0) {
                const orderId = response.body.data[0].orderId
                cy.wrap(orderId).as('testOrderId')
            }
        })
    })

    it('获取单个订单详情 - GET /order/get-one-order', function () {
        // 使用之前测试中保存的订单ID或环境变量中的测试订单ID
        const orderId = this.testOrderId || Cypress.env('testOrderId')

        cy.request({
            method: 'GET',
            url: `${Cypress.env('apiUrl')}/order/get-one-order`,
            headers: cy.getAuthHeader(),
            qs: {
                orderId: orderId
            }
        }).then((response) => {
            expect(response.status).to.eq(200)
            expect(response.body).to.have.property('code', 200)
            expect(response.body.data).to.have.property('orderId', orderId)
        })
    })

    it('添加商品评价 - PUT /order/remark-order-item', function () {
        const orderId = this.testOrderId || Cypress.env('testOrderId')

        const remarkData = {
            orderId: orderId,
            productId: Cypress.env('testProductId'),
            remark: '商品质量很好，发货速度快',
            rating: 5
        }

        cy.request({
            method: 'PUT',
            url: `${Cypress.env('apiUrl')}/order/remark-order-item`,
            headers: cy.getAuthHeader(),
            body: remarkData
        }).then((response) => {
            expect(response.status).to.eq(200)
            expect(response.body).to.have.property('code', 200)
        })
    })

    it('获取店铺所有评价 - GET /order/get-store-remarks', () => {
        cy.request({
            method: 'GET',
            url: `${Cypress.env('apiUrl')}/order/get-store-remarks`,
            headers: cy.getAuthHeader(),
            qs: {
                storeId: Cypress.env('testStoreId')
            }
        }).then((response) => {
            expect(response.status).to.eq(200)
            expect(response.body).to.have.property('code', 200)
            expect(response.body.data).to.be.an('array')

            // 如果之前添加了评价，可以验证是否包含
            if (response.body.data.length > 0) {
                expect(response.body.data[0]).to.have.property('storeId', Cypress.env('testStoreId'))
            }
        })
    })
  })