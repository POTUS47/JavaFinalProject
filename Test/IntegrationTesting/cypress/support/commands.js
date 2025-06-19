// cypress/support/commands.js
// Cypress.Commands.add('login', () => {
//     cy.request('POST', `${Cypress.env('apiUrl')}/users/login`, {
//         identifier: Cypress.env('testUser').identifier,
//         password: Cypress.env('testUser').password
//     }).then((response) => {
//         expect(response.status).to.eq(200)
//         window.localStorage.setItem('token', response.body.data.JwtToken)
//     })
// })

Cypress.Commands.add('login', (email, password) => {
    cy.request({
        method: 'POST',
        url: `${Cypress.env('apiUrl')}/users/login`,
        body: {
            identifier: email,
            password: password
        },
        failOnStatusCode: false // 允许测试失败登录
    }).then((response) => {
        console.log('【浏览器控制台】完整响应:', response); // 浏览器控制台打印
        console.log('【Cypress日志】状态码:', response.status); // Cypress命令行打印
        console.log('【Cypress日志】响应体:', response.body);
        if (response.status === 200) {
            window.localStorage.setItem('token', response.body.data.JwtToken);
            const token = window.localStorage.getItem('token');
            console.log('【浏览器】localStorage.token:', token);
        }
        return response;
    });
});

// // cypress/support/commands.js
// Cypress.Commands.add('login', () => {
//     cy.log('开始登录，请求URL:', `${Cypress.env('apiUrl')}/users/login`); // 打印请求地址
//     cy.log('登录账号:', Cypress.env('testUser')); // 打印账号密码

//     cy.request('POST', `${Cypress.env('apiUrl')}/users/login`, {
//         identifier: Cypress.env('testUser').identifier,
//         password: Cypress.env('testUser').password
//     }).then((response) => {
//         console.log('【浏览器控制台】完整响应:', response); // 浏览器控制台打印
//         cy.log('【Cypress日志】状态码:', response.status); // Cypress命令行打印
//         cy.log('【Cypress日志】响应体:', response.body);

//         if (!response.body.data.JwtToken) {
//             cy.log('❌ 响应中没有token字段！请检查后端返回数据结构');
//             console.error('响应体缺失token:', response.body);
//         } else {
//             window.localStorage.setItem('token', response.body.data.JwtToken);
//             cy.log('✅ Token已存储:', response.body.token);
//             console.log('【浏览器】localStorage.token:', window.localStorage.getItem('token'));
//         }
//     });
// });

Cypress.Commands.add('getAuthHeader', () => {
    const token = window.localStorage.getItem('token')
    return {
        'Authorization': token,
        'Content-Type': 'application/json'
    }
})
  

// cypress/support/commands.js
Cypress.Commands.add('cleanupTestData', () => {
    // 清理测试创建的收藏、评价等数据
    const token = window.localStorage.getItem('token')

    // 示例：清理测试收藏
    cy.request({
        method: 'DELETE',
        url: `${Cypress.env('apiUrl')}/favourite/remove-product`,
        headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
        },
        body: {
            productId: Cypress.env('testProductId')
        },
        failOnStatusCode: false // 允许404等状态码
    })

    // 清理测试评价
    cy.request({
        method: 'DELETE',
        url: `${Cypress.env('apiUrl')}/order/remove-remark`,
        headers: {
            'Authorization': `${token}`,
            'Content-Type': 'application/json'
        },
        body: {
            orderId: Cypress.env('testOrderId'),
            productId: Cypress.env('testProductId')
        },
        failOnStatusCode: false
    })
})

// 在测试文件中使用
// afterEach(() => {
//     cy.cleanupTestData()
//   })