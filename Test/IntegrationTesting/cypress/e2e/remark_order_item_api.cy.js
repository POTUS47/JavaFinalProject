// describe('订单管理接口测试', () => {
//     beforeEach(() => {
//         cy.login()
//     })


//     it('添加商品评价 - PUT /order/remark-order-item', function () {
//         const token = window.localStorage.getItem('token');

//         const remarkData = {
//             orderItemId: "529238668238848",
//             score: 2,
//             remark: '商品质量很好，但发货速度很慢',
//         }

//         cy.request({
//             method: 'PUT',
//             url: `${Cypress.env('apiUrl')}/shopping/order/remark-order-item`,
//             headers: {
//                 'Authorization': token,
//                 'Accept': 'application/json'
//             },
//             body: remarkData
//         }).then((response) => {
//             expect(response.status).to.eq(200)
//             expect(response.body).to.have.property('code', 200)
//         })
//     })
// })


describe('订单评价接口测试', () => {
    // 测试用例1：正常评价
    it('TC_REMARKO01 - 正常评价', () => {
        cy.request({
            method: 'PUT',
            url: `${Cypress.env('apiUrl')}/shopping/order/remark-order-item`,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: {
                orderItemId: "529238668238848",
                score: 3,
                remark: "商品很好"
            }
        }).then((response) => {
            expect(response.status).to.eq(200);
            expect(response.body.code).to.eq(200);
            expect(response.body.message).to.eq('订单评价已提交');
        });
    });

    // 测试用例2：评价内容为空（只打分）
    it('TC_REMARKO02 - 评价内容为空', () => {
        cy.request({
            method: 'PUT',
            url: `${Cypress.env('apiUrl')}/shopping/order/remark-order-item`,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: {
                orderItemId: "529238668238848",
                score: 3,
                remark: null
            },
            failOnStatusCode: false
        }).then((response) => {
            expect(response.status).to.eq(200);
            expect(response.body.code).to.eq(200);
        });
    });

    // 测试用例3：score 超出范围（负数）
    it('TC_REMARKO03 - score为负数', () => {
        cy.request({
            method: 'PUT',
            url: `${Cypress.env('apiUrl')}/shopping/order/remark-order-item`,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: {
                orderItemId: "529238668238848",
                score: -5,
                remark: "商品很好"
            },
            failOnStatusCode: false
        }).then((response) => {
            expect(response.status).to.eq(404);
            expect(response.body.code).to.eq(404);
            expect(response.body.message).to.eq('评分只能在0-5之间');
        });
    });

    // 测试用例4：score 超出范围（>5）
    it('TC_REMARKO04 - score超过5', () => {
        cy.request({
            method: 'PUT',
            url: `${Cypress.env('apiUrl')}/shopping/order/remark-order-item`,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: {
                orderItemId: "529238668238848",
                score: 6,
                remark: "商品很好"
            },
            failOnStatusCode: false
        }).then((response) => {
            expect(response.status).to.eq(404);
            expect(response.body.code).to.eq(404);
            expect(response.body.message).to.eq('评分只能在0-5之间');
        });
    });

    // 测试用例5：remark超200字
    it('TC_REMARKO05 - remark超长', () => {
        const longRemark = "这个商品整体来说体验还是比较不错的，包装非常结实，物流也很快，几乎是下单后第二天就到了。商品本身做工精细，没有发现瑕疵，材质和描述一致，手感很好。使用起来非常顺手，功能也很齐全，满足了我的预期需求。客服的服务态度也很好，能够及时解答问题，处理效率很高。虽然价格稍微有点贵，但是总体性价比还是可以接受的。唯一美中不足的是说明书略显简单，新手上手可能要多花一点时间。总体来说我很满意这次购物体验，推荐给其他用户";

        cy.request({
            method: 'PUT',
            url: `${Cypress.env('apiUrl')}/shopping/order/remark-order-item`,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: {
                orderItemId: "529238668238848",
                score: 3,
                remark: longRemark
            },
            failOnStatusCode: false
        }).then((response) => {
            expect(response.status).to.eq(500);
            expect(response.body.code).to.eq(500);
            expect(response.body.message).to.eq('remark超出200的长度限制');
        });
    });

    // 测试用例6：orderId为空
    it('TC_REMARKO06 - orderId为空', () => {
        cy.request({
            method: 'PUT',
            url: `${Cypress.env('apiUrl')}/shopping/order/remark-order-item`,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: {
                orderItemId: null,
                score: 3,
                remark: "商品很好"
            },
            failOnStatusCode: false
        }).then((response) => {
            expect(response.status).to.eq(404);
            expect(response.body.code).to.eq(404);
            expect(response.body.message).to.eq('orderItemId不能为空');
        });
    });

    // 测试用例7：orderId不存在
    it('TC_REMARKO07 - orderId不存在', () => {
        cy.request({
            method: 'PUT',
            url: `${Cypress.env('apiUrl')}/shopping/order/remark-order-item`,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: {
                orderItemId: "529238661234561",
                score: 3,
                remark: "商品很好"
            },
            failOnStatusCode: false
        }).then((response) => {
            expect(response.status).to.eq(404);
            expect(response.body.code).to.eq(404);
            expect(response.body.message).to.eq('未找到订单');
        });
    });

    // 测试用例8：score为空
    it('TC_REMARKO08 - score为空', () => {
        cy.request({
            method: 'PUT',
            url: `${Cypress.env('apiUrl')}/shopping/order/remark-order-item`,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: {
                orderItemId: "529238668238848",
                score: null,
                remark: "商品很好"
            },
            failOnStatusCode: false
        }).then((response) => {
            expect(response.status).to.eq(400);
            expect(response.body.code).to.eq(400);
        });
    });

    // 测试用例9：所有字段为null
    it('TC_REMARKO09 - 所有字段为null', () => {
        cy.request({
            method: 'PUT',
            url: `${Cypress.env('apiUrl')}/shopping/order/remark-order-item`,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: {
                orderItemId: null,
                score: null,
                remark: null
            },
            failOnStatusCode: false
        }).then((response) => {
            expect(response.status).to.eq(400);
            expect(response.body.code).to.eq(400);
        });
    });
});