import { test, expect } from '@playwright/test';

test('查看商品详情', async ({ page }) => {
  await page.goto('http://localhost:5173/loginandregister');
  await page.locator('div').filter({ hasText: /^忘记密码\?登录$/ }).locator('div').nth(1).click();
  await page.getByRole('textbox', { name: '请输入邮箱' }).fill('946645913@qq.com');
  await page.getByRole('textbox', { name: '请输入密码' }).dblclick();
  await page.getByRole('textbox', { name: '请输入密码' }).press('ControlOrMeta+Insert');
  await page.getByRole('textbox', { name: '请输入密码' }).fill('123');
  await page.getByRole('button', { name: '登录' }).click();
  await page.getByRole('link', { name: '商品' }).click();
  console.log(await page.content());
  await page.getByRole('img', { name: '匠心器皿' }).click();
  await page.locator('.product-item').first().click();
});
