// cypress/e2e/get_favorite_api.cy.js
// describe('收藏商品接口测试', () => {
//     beforeEach(() => {
//         cy.login()
//     })

//     it('获取收藏商品列表 - GET /shopping/favourite/get-favourite-products', () => {
        
//         const token = window.localStorage.getItem('token');

//         cy.request({
//             method: 'GET',
//             url: `${Cypress.env('apiUrl')}/shopping/favourite/get-favourite-products`,
//             headers: {
//                 'Authorization': token,
//                 'Accept': 'application/json'
//               },
//         }).then((response) => {
//             expect(response.status).to.eq(200)
//             expect(response.body).to.have.property('code', 200)
//             expect(response.body.data).to.be.an('array')
//         })
//     })

//   })

// cypress/e2e/get_favorite_api.cy.js
// describe('收藏商品接口测试', () => {

//     it('TC_FAVPRO01 - 用户有收藏商品', () => {
//         // 登录并等待完成
//         cy.login('2923289295@qq.com', '123').then(() => {
//             // 在这里获取token才是安全的
//             const token = window.localStorage.getItem('token');
//             console.log('正确的token:', token);

//             cy.request({
//                 method: 'GET',
//                 url: `${Cypress.env('apiUrl')}/shopping/favourite/get-favourite-products`,
//                 headers: {
//                     'Authorization': token,
//                     'Accept': 'application/json'
//                 }
//             }).then((response) => {
//                 // 断言
//                 expect(response.status).to.eq(200);
//                 expect(response.body.code).to.eq(200);
//                 expect(response.body.data).to.be.an('array');
//                 expect(response.body.data.length).to.be.gt(0);
//             });
//         });
//       });


//     // 测试用例2：用户没有收藏
//     it('TC_FAVPRO02 - 用户没有收藏商品', () => {
//         // 登录
//         cy.login('946645913@qq.com', '123');

//         // 请求接口（允许404）
//         cy.request({
//             method: 'GET',
//             url: `${Cypress.env('apiUrl')}/shopping/favourite/get-favourite-products`,
//             headers: {
//                 'Authorization': window.localStorage.getItem('token')
//             },
//             failOnStatusCode: false
//         }).then((response) => {
//             // 断言
//             expect(response.status).to.eq(404);
//             expect(response.body.code).to.eq(404);
//         });
//     });

//     // 测试用例3：未登录访问
//     it('TC_FAVPRO03 - 未授权访问', () => {
//         // 不登录，直接清除token
//         window.localStorage.removeItem('token');

//         // 请求接口（预期401）
//         cy.request({
//             method: 'GET',
//             url: `${Cypress.env('apiUrl')}/shopping/favourite/get-favourite-products`,
//             failOnStatusCode: false
//         }).then((response) => {
//             // 断言
//             expect(response.status).to.eq(401);
//             expect(response.body.code).to.eq(401);
//         });
//     });

//     // 可以继续添加更多测试用例...
//     it('TC_FAVPRO04 - 无效token访问', () => {
//         // 设置无效token
//         window.localStorage.setItem('token', 'invalid_token_123');

//         // 请求接口
//         cy.request({
//             method: 'GET',
//             url: `${Cypress.env('apiUrl')}/shopping/favourite/get-favourite-products`,
//             headers: {
//                 'Authorization': 'invalid_token_123'
//             },
//             failOnStatusCode: false
//         }).then((response) => {
//             // 断言
//             expect(response.status).to.eq(401);
//         });
//     });
// });


describe('收藏商品接口测试', () => {
    // 测试用例1：用户有收藏
    it('TC_FAVPRO01 - 用户有收藏商品', () => {
        // 登录并等待完成
        cy.login('2923289295@qq.com', '123').then(() => {
            const token = window.localStorage.getItem('token');
            console.log('正确的token:', token);

            cy.request({
                method: 'GET',
                url: `${Cypress.env('apiUrl')}/shopping/favourite/get-favourite-products`,
                headers: {
                    'Authorization': token,
                    'Accept': 'application/json'
                }
            }).then((response) => {
                expect(response.status).to.eq(200);
                expect(response.body.code).to.eq(200);
                expect(response.body.data).to.be.an('array');
                expect(response.body.data.length).to.be.gt(0);
            });
        });
    });

    // 测试用例2：用户没有收藏
    it('TC_FAVPRO02 - 用户没有收藏商品', () => {
        // 登录并等待完成
        cy.login('946645913@qq.com', '123').then(() => {
            const token = window.localStorage.getItem('token');
            console.log('正确的token:', token);

            cy.request({
                method: 'GET',
                url: `${Cypress.env('apiUrl')}/shopping/favourite/get-favourite-products`,
                headers: {
                    'Authorization': token,
                    'Accept': 'application/json'
                },
                failOnStatusCode: false
            }).then((response) => {
                expect(response.status).to.eq(404);
                expect(response.body.code).to.eq(404);
            });
        });
    });

    // 测试用例3：未登录访问
    it('TC_FAVPRO03 - 用户未登陆', () => {
        // 不登录，直接清除token
        window.localStorage.removeItem('token');

        cy.request({
            method: 'GET',
            url: `${Cypress.env('apiUrl')}/shopping/favourite/get-favourite-products`,
            headers: {
                'Accept': 'application/json'
            },
            failOnStatusCode: false
        }).then((response) => {
            expect(response.status).to.eq(401);
            expect(response.body.code).to.eq(401);
        });
    });

    
});