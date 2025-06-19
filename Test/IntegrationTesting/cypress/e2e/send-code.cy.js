describe('发送验证码接口测试', () => {
    const API_URL = `${Cypress.env('apiUrl')}/users/send-code`;

    // 测试用例1：合法邮箱发送成功
    it('TC_SENDEMAILO01 - 合法邮箱发送成功', () => {
        const validEmail = "2923289295@qq.com";

        cy.request({
            method: 'GET',
            url: `${API_URL}/${validEmail}`,
        }).then((response) => {
            expect(response.status).to.eq(200);
            expect(response.body.code).to.eq(200);
            expect(response.body.data.verificationCode).to.match(/^\d{6}$/);
        });
    });

    // 测试用例2：非法邮箱格式
    it('TC_SENDEMAILO02 - 非法邮箱格式', () => {
        const invalidEmail = "123qq.com"; // 根据业务规则调整

        cy.request({
            method: 'GET',
            url: `${API_URL}/${invalidEmail}`,
            failOnStatusCode: false
        }).then((response) => {
            expect(response.status).to.eq(400);
            expect(response.body.code).to.eq(400);
            expect(response.body.message).to.match(/邮箱格式错误|非法邮箱/);
        });
    });

    // 测试用例3：空邮箱参数
    it('TC_SENDEMAILO03 - 邮箱参数为空', () => {
        // 测试null路径参数
        cy.request({
            method: 'GET',
            url: `${API_URL}/null`, // 或 `${API_URL}/`
            failOnStatusCode: false
        }).then((response) => {
            expect(response.status).to.eq(400);
            expect(response.body.code).to.eq(400);
        });
    });
});