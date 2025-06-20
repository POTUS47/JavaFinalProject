import { test, expect } from '@playwright/test';

test('登录成功跳转首页', async ({ page }) => {
  await page.goto('http://localhost:5173/loginandregister');

  await page.getByRole('textbox', { name: '请输入邮箱' }).fill('946645913@qq.com');
  await page.getByPlaceholder('请输入密码').fill('123');  // 如果密码框有placeholder属性

  await page.getByRole('button', { name: '登录' }).click();

  // 断言登录后跳转到首页
  await expect(page).toHaveURL('http://localhost:5173/home');
  
});
