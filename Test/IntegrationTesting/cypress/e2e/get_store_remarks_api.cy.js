describe('订单管理接口测试', () => {
    beforeEach(() => {
        cy.login()
    })

    it('获取店铺所有评价 - GET /order/get-store-remarks', () => {
        const token = window.localStorage.getItem('token');

        cy.request({
            method: 'GET',
            url: `${Cypress.env('apiUrl')}/shopping/order/get-store-remarks`,
            
            qs: {
                storeId: "529233017020416"
            }
        }).then((response) => {
            expect(response.status).to.eq(200)
            expect(response.body).to.have.property('code', 200)
            expect(response.body.data).to.be.an('array')

            // 如果之前添加了评价，可以验证是否包含
            if (response.body.data.length > 0) {
                expect(response.body.data[0]).to.have.property('orderScore')
            }
        })
    })
})