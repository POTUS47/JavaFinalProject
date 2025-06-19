describe('获取买家所有订单接口测试', () => {
    const API_URL = `${Cypress.env('apiUrl')}/shopping/order/get-all-buyer-orders`;

    // 测试用例1：用户有订单
    it('TC_ALLORDERO01 - 用户有订单', () => {
        // 先登录获取token
        cy.login('2923289295@qq.com', '123').then(() => {
            const token = window.localStorage.getItem('token');

            cy.request({
                method: 'GET',
                url: API_URL,
                headers: {
                    'Authorization': token,
                    'Accept': 'application/json'
                }
            }).then((response) => {
                expect(response.status).to.eq(200);
                expect(response.body.code).to.eq(200);
                expect(response.body.data).to.be.an('array').that.is.not.empty;
                // 验证订单基础字段
                
            });
        });
    });

    // 测试用例2：用户没有订单
    it('TC_ALLORDERO02 - 用户没有订单', () => {
        cy.login('2645779070@qq.com', '123').then(() => {
            const token = window.localStorage.getItem('token');

            cy.request({
                method: 'GET',
                url: API_URL,
                headers: {
                    'Authorization': token,
                    'Accept': 'application/json'
                }
            }).then((response) => {
                expect(response.status).to.eq(200);
                expect(response.body.code).to.eq(200);
                expect(response.body.data).to.be.an('array').that.is.empty;
                // 可选：验证空数据时的提示消息
                expect(response.body.message).to.eq('无相关用户订单');
            });
        });
    });

    // 测试用例3：用户未登录
    it('TC_ALLORDERO03 - 用户未登录', () => {
        // 确保清除token
        window.localStorage.removeItem('token');

        cy.request({
            method: 'GET',
            url: API_URL,
            headers: {
                'Accept': 'application/json'
            },
            failOnStatusCode: false
        }).then((response) => {
            expect(response.status).to.eq(401);
            expect(response.body.code).to.eq(401);
            expect(response.body.message).to.eq('Unauthorized');
        });
    });
});