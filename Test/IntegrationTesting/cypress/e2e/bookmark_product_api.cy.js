describe('收藏商品接口测试', () => {
    // 测试用例1：正常收藏商品
    it('TC_FAVPRO01 - 正常收藏商品', () => {
        cy.login('2923289295@qq.com', '123').then(() => {
            const token = window.localStorage.getItem('token');

            cy.request({
                method: 'POST',
                url: `${Cypress.env('apiUrl')}/shopping/favourite/bookmark-product`,
                headers: {
                    'Authorization': token,
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: {
                    productId: "p529235889053696"
                }
            }).then((response) => {
                expect(response.status).to.eq(200);
                expect(response.body.code).to.eq(200);
                expect(response.body.message).to.eq('收藏成功');
            });
        });
    });

    // 测试用例2：取消收藏已经收藏过的商品
    it('TC_FAVPRO02 - 取消收藏已经收藏过的商品', () => {
        cy.login('2923289295@qq.com', '123').then(() => {
            const token = window.localStorage.getItem('token');

            cy.request({
                method: 'POST',
                url: `${Cypress.env('apiUrl')}/shopping/favourite/bookmark-product`,
                headers: {
                    'Authorization': token,
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: {
                    productId: "p529234076823552"
                }
            }).then((response) => {
                expect(response.status).to.eq(200);
                expect(response.body.code).to.eq(200);
                expect(response.body.message).to.eq('取消收藏成功');
            });
        });
    });

    // 测试用例3：收藏不存在的商品
    it('TC_FAVPRO03 - 收藏不存在的商品', () => {
        cy.login('2923289295@qq.com', '123').then(() => {
            const token = window.localStorage.getItem('token');

            cy.request({
                method: 'POST',
                url: `${Cypress.env('apiUrl')}/shopping/favourite/bookmark-product`,
                headers: {
                    'Authorization': token,
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: {
                    productId: "p1001"
                },
                failOnStatusCode: false
            }).then((response) => {
                expect(response.status).to.eq(404);
                expect(response.body.code).to.eq(404);
                expect(response.body.message).to.eq('未找到商品信息');
            });
        });
    });

    // 测试用例4：商品id为空
    it('TC_FAVPRO04 - 商品id为空', () => {
        cy.login('2923289295@qq.com', '123').then(() => {
            const token = window.localStorage.getItem('token');

            cy.request({
                method: 'POST',
                url: `${Cypress.env('apiUrl')}/shopping/favourite/bookmark-product`,
                headers: {
                    'Authorization': token,
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: {
                    productId: null
                },
                failOnStatusCode: false
            }).then((response) => {
                expect(response.status).to.eq(400);
                expect(response.body.code).to.eq(400);
            });
        });
    });

    // 测试用例5：用户未登录
    it('TC_FAVPRO05 - 用户未登录', () => {
        window.localStorage.removeItem('token');

        cy.request({
            method: 'POST',
            url: `${Cypress.env('apiUrl')}/shopping/favourite/bookmark-product`,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: {
                productId: "p529235889053696"
            },
            failOnStatusCode: false
        }).then((response) => {
            expect(response.status).to.eq(401);
            expect(response.body.code).to.eq(401);
            expect(response.body.message).to.eq('Unauthorized');
        });
    });
});