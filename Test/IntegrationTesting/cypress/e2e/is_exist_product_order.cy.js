describe('商品订单存在检查接口测试', () => {
    const API_URL = `${Cypress.env('apiUrl')}/shopping/order/is-exist-product-order`;

    // 测试用例1：存在订单的商品
    it('TC_EXISTPRO01 - 存在订单的商品', () => {
        cy.request({
            method: 'GET',
            url: API_URL,
            qs: {
                productId: "p529235889053696"
            }
        }).then((response) => {
            expect(response.status).to.eq(200);
            expect(response.body.code).to.eq(200);
            expect(response.body.data).to.be.true;
        });
    });

    // 测试用例2：商品存在但没有被下单
    it('TC_EXISTPRO02 - 商品存在但无订单', () => {
        cy.request({
            method: 'GET',
            url: API_URL,
            qs: {
                productId: "p532746239836160"
            }
        }).then((response) => {
            expect(response.status).to.eq(200);
            expect(response.body.code).to.eq(200);
            expect(response.body.data).to.be.false;
        });
    });

    // 测试用例3：商品ID不存在
    it('TC_EXISTPRO03 - 商品ID不存在', () => {
        cy.request({
            method: 'GET',
            url: API_URL,
            qs: {
                productId: "p529234076812345"
            },
            failOnStatusCode: false
        }).then((response) => {
            expect(response.status).to.eq(404);
            expect(response.body.code).to.eq(404);
            expect(response.body.message).to.eq('商品不存在');
        });
    });

    // 测试用例4：商品ID为空
    it('TC_EXISTPRO04 - 商品ID为空', () => {
        // 测试null情况
        cy.request({
            method: 'GET',
            url: API_URL,
            qs: {
                productId: null
            },
            failOnStatusCode: false
        }).then((response) => {
            expect(response.status).to.eq(400);
            expect(response.body.code).to.eq(400);
            expect(response.body.message).to.eq('productId 不能为空');
        });

        // 测试空字符串情况
        cy.request({
            method: 'GET',
            url: API_URL,
            qs: {
                productId: ''
            },
            failOnStatusCode: false
        }).then((response) => {
            expect(response.status).to.eq(400);
        });
    });
});