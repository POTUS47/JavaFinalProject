// cypress/e2e/favourite_api.cy.js
describe('收藏商品接口测试', () => {
    beforeEach(() => {
        cy.login()
    })

    it('获取收藏商品列表 - GET /shopping/favourite/get-favourite-products', () => {
        const token = window.localStorage.getItem('token');
        // cy.log('Token:', window.localStorage.getItem('token'));
        // cy.log('Headers:', cy.getAuthHeader());

        cy.request({
            method: 'GET',
            url: `${Cypress.env('apiUrl')}/shopping/favourite/get-favourite-products`,
            headers: {
                'Authorization': token,
                'Accept': 'application/json'
              },
        }).then((response) => {
            expect(response.status).to.eq(200)
            expect(response.body).to.have.property('code', 200)
            expect(response.body.data).to.be.an('array')
        })
    })

    it('收藏商品 - POST /shopping/favourite/bookmark-product', () => {
        const token = window.localStorage.getItem('token');
        cy.request({
            method: 'POST',
            url: `${Cypress.env('apiUrl')}/shopping/favourite/bookmark-product`,
            headers: {
                'Authorization': token,
                'Accept': 'application/json'
            },
            body: {
                productId: "p529234076823552"
            }
        }).then((response) => {
            expect(response.status).to.eq(200)
            expect(response.body).to.have.property('code', 200)
            // expect(response.body.data).to.be.a('string')
        })
    })
  })