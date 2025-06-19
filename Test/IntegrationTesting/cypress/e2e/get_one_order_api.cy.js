describe('获取单个订单接口测试', () => {
    const API_URL = `${Cypress.env('apiUrl')}/shopping/order/get-one-order`;

    // 测试用例1：查询存在的订单
    it('TC_ONEORDERO01 - 查询存在的订单', () => {
        const validOrderId = "529238668013568";
        cy.request({
            method: 'GET',
            url: API_URL,
            qs: { orderId: validOrderId },
        }).then((response) => {
            expect(response.status).to.eq(200);
            expect(response.body.code).to.eq(200);
            expect(response.body.data).to.have.property('orderId', validOrderId);
        });
    });

    // 测试用例2：查询不存在的订单
    it('TC_ONEORDERO02 - 查询不存在的订单', () => {
        const invalidOrderId = "223344";
        cy.request({
            method: 'GET',
            url: API_URL,
            qs: { orderId: invalidOrderId },
            failOnStatusCode: false,
        }).then((response) => {
            expect(response.status).to.eq(404);
            expect(response.body.code).to.eq(404);
            expect(response.body.message).to.eq('订单不存在');
        });
    });

    // 测试用例3：orderId为空
    it('TC_ONEORDERO03 - orderId为空', () => {
        // 测试 null
        cy.request({
            method: 'GET',
            url: API_URL,
            qs: { orderId: null },
            failOnStatusCode: false,
        }).then((response) => {
            expect(response.status).to.eq(400);
            expect(response.body.code).to.eq(400);
            expect(response.body.message).to.match(/缺少必要参数|orderId不能为空/);
        });

        // 测试空字符串
        cy.request({
            method: 'GET',
            url: API_URL,
            qs: { orderId: '' },
            failOnStatusCode: false,
        }).then((response) => {
            expect(response.status).to.eq(400);
        });
    });
});