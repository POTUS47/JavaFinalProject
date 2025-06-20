import { test, expect } from '@playwright/test';

test('查看商品分类', async ({ page }) => {
  await page.goto('http://localhost:5173/loginandregister');
  await page.getByRole('textbox', { name: '请输入邮箱' }).fill('2923289295@qq.com');
  await page.getByRole('textbox', { name: '请输入密码' }).fill('123');
  await page.getByRole('button', { name: '登录' }).click();
  await page.getByRole('link', { name: '商品' }).click();
  await page.getByRole('img', { name: '匠心器皿' }).click();
  await page.getByText('瓷器', { exact: true }).click();
  await page.getByText('陶器', { exact: true }).click();
});