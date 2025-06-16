describe('订单管理接口测试', () => {
    beforeEach(() => {
        cy.login()
    })


    it('添加商品评价 - PUT /order/remark-order-item', function () {
        const token = window.localStorage.getItem('token');

        const remarkData = {
            orderItemId: "529238668238848",
            score: 2,
            remark: '商品质量很好，但发货速度很慢',
        }

        cy.request({
            method: 'PUT',
            url: `${Cypress.env('apiUrl')}/shopping/order/remark-order-item`,
            headers: {
                'Authorization': token,
                'Accept': 'application/json'
            },
            body: remarkData
        }).then((response) => {
            expect(response.status).to.eq(200)
            expect(response.body).to.have.property('code', 200)
        })
    })
})