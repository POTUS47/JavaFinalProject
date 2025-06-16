describe('订单管理接口测试', () => {
    beforeEach(() => {
        cy.login()
    })

    it('获取买家所有订单 - GET /order/get-all-buyer-orders', () => {
        const token = window.localStorage.getItem('token');

        cy.request({
            method: 'GET',
            url: `${Cypress.env('apiUrl')}/shopping/order/get-all-buyer-orders`,
            headers: {
                'Authorization': token,
                'Accept': 'application/json'
            },
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
})