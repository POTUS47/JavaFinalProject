import { test, expect } from '@playwright/test';

test('查看收藏夹', async ({ page }) => {
  await page.goto('http://localhost:5173/loginandregister');
  await page.locator('div').filter({ hasText: /^忘记密码\?登录$/ }).locator('div').nth(1).click();
  await page.getByRole('textbox', { name: '请输入邮箱' }).fill('2923289295@qq.com');
  await page.getByRole('textbox', { name: '请输入密码' }).fill('123');
  await page.getByRole('button', { name: '登录' }).click();
  await page.getByRole('link', { name: '收藏夹' }).click();
  await page.getByRole('complementary').click();
  await page.getByRole('treeitem', { name: '收藏店铺' }).click();
  await page.getByRole('treeitem', { name: '收藏商品' }).click();
  await page.getByRole('img', { name: 'p529235889053696' }).click();
});