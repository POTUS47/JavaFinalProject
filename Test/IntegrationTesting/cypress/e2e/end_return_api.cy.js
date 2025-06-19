describe('结束售后流程接口测试', () => {
    const API_BASE = `${Cypress.env('apiUrl')}/shopping/order/end_return`;

    // 测试用例1：正常结束售后
    it('TC_ENDRETURNO1 - 正常结束售后', () => {
        const validItemId = "532741415931904";

        cy.request({
            method: 'PUT',
            url: `${API_BASE}/${validItemId}`,
        }).then((response) => {
            expect(response.status).to.eq(200);
            expect(response.body.code).to.eq(200);
        });
    });

    // 测试用例2：订单项不存在
    it('TC_ENDRETURNO2 - 订单项不存在', () => {
        const invalidItemId = "529238668231234";

        cy.request({
            method: 'PUT',
            url: `${API_BASE}/${invalidItemId}`,
            failOnStatusCode: false
        }).then((response) => {
            expect(response.status).to.eq(404);
            expect(response.body.code).to.eq(404);
            expect(response.body.message).to.eq('希望修改状态的订单项不存在！');
        });
    });

    // 测试用例3：状态不合法
    it('TC_ENDRETURNO3 - 状态不合法', () => {
        const wrongStatusItemId = "529238668238848";

        cy.request({
            method: 'PUT',
            url: `${API_BASE}/${wrongStatusItemId}`,
            failOnStatusCode: false
        }).then((response) => {
            expect(response.status).to.eq(400);
            expect(response.body.code).to.eq(400);
            expect(response.body.message).to.eq('当前状态无法结束售后！');
        });
    });

    // 测试用例4：参数为空
    it('TC_ENDRETURNO4 - item_id为空', () => {
        // 测试路径参数为null（需后端能处理）
        cy.request({
            method: 'PUT',
            url: `${API_BASE}/`, // 或 `${API_BASE}/`
            failOnStatusCode: false
        }).then((response) => {
            expect(response.status).to.eq(400);
            expect(response.body.code).to.eq(400);
            expect(response.body.message).to.match(/item_id 不可为空|参数错误/);
        });
    });
});