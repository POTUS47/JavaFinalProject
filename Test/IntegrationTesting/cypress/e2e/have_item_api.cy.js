describe('检查用户订单项权限接口测试', () => {
    const API_BASE = `${Cypress.env('apiUrl')}/shopping/order/buyer/have_item`;

    // 测试用例1：用户拥有该订单项
    it('TC_EXITITEMO01 - 用户拥有订单项', () => {
        const userId = "529237152305152";
        const itemId = "529238668238848";

        cy.request({
            method: 'GET',
            url: `${API_BASE}/${userId}/${itemId}`,
        }).then((response) => {
            expect(response.status).to.eq(200);
            expect(response.body.code).to.eq(200);
        });
    });

    // 测试用例2-4：无权限/不存在场景（合并相似用例）
    [
        {
            case: 'TC_EXITITEMO02',
            userId: '529237152305152',
            itemId: '532745428963328',
            desc: '用户没有该订单项'
        },
        {
            case: 'TC_EXITITEMO03',
            userId: '529237152312345',
            itemId: '529238668238848',
            desc: '用户不存在'
        },
        {
            case: 'TC_EXITITEMO04',
            userId: '529237152305152',
            itemId: '529238668212345',
            desc: '订单项不存在'
        }
    ].forEach(({ case: caseId, userId, itemId, desc }) => {
        it(`${caseId} - ${desc}`, () => {
            cy.request({
                method: 'GET',
                url: `${API_BASE}/${userId}/${itemId}`,
                failOnStatusCode: false
            }).then((response) => {
                expect(response.status).to.eq(404);
                expect(response.body.code).to.eq(404);
                expect(response.body.message).to.match(/订单项不存在|无权限访问/);
            });
        });
    });

    // 测试用例5-6：参数为空
    [
        { case: 'TC_EXITITEMO05', userId: null, itemId: '529238668238848' },
        { case: 'TC_EXITITEMO06', userId: '529237152305152', itemId: null }
    ].forEach(({ case: caseId, userId, itemId }) => {
        it(`${caseId} - 参数为${userId === null ? '用户ID空' : '订单项ID空'}`, () => {
            // 处理null路径参数的特殊情况
            const url = userId === null
                ? `${API_BASE}//${userId}`  // 双斜杠表示空路径参数
                : `${API_BASE}/${itemId}/`;

            cy.request({
                method: 'GET',
                url,
                failOnStatusCode: false
            }).then((response) => {
                expect(response.status).to.eq(400);
            });
        });
    });

    // [
    //     { case: 'TC_EXITITEMO05', userId: 'null', itemId: '529238668238848' },
    //     { case: 'TC_EXITITEMO06', userId: '529237152305152', itemId: 'null' },
    // ].forEach(({ case: caseId, userId, itemId }) => {
    //     it(`${caseId} - 参数为${userId === 'null' || userId === '' ? '用户ID空' : '订单项ID空'}`, () => {
    //         // 构造 URL：注意这里允许传递空或 null 字符串作为路径变量
    //         const url = `${API_BASE}/${userId}/${itemId}`;

    //         cy.request({
    //             method: 'GET',
    //             url,
    //             failOnStatusCode: false
    //         }).then((response) => {
    //             expect(response.status).to.eq(400); // 应该能进到 controller 并返回 400
    //         });
    //     });
    // });
});