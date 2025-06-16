describe('订单管理接口测试', () => {
    beforeEach(() => {
        cy.login()
    })

    it('获取单个订单详情 - GET /order/get-one-order', function () {
        // 使用之前测试中保存的订单ID或环境变量中的测试订单ID
        const orderId = "529238668013568"
        const token = window.localStorage.getItem('token');

        cy.request({
            method: 'GET',
            url: `${Cypress.env('apiUrl')}/shopping/order/get-one-order`,
            headers: {
                'Authorization': token,
                'Accept': 'application/json'
            },
            qs: {
                orderId: orderId
            }
        }).then((response) => {
            expect(response.status).to.eq(200)
            expect(response.body).to.have.property('code', 200)
            expect(response.body.data).to.have.property('orderId', orderId)
        })
    })
})