import { Routes } from '@angular/router';
import { LoginComponent } from './features/pages/login/login.component';
import { AuthGuard } from './infrastructures/authguard/authguard';
import { RegisterComponent } from './features/pages/register/register.component';

export const routes: Routes = [
  // ログインページ（認証不要）
  { path: 'login', component: LoginComponent },
  // 新規登録ページ（認証不要）
  { path: 'register', component: RegisterComponent },
  // 保護されたページ（認証必須）
  {
    path: 'pages',
    loadChildren: () =>
      import('./features/pages/pages.routes').then((m) => m.default), // `default` を使用しない
    canActivate: [AuthGuard], // `AuthGuard` でアクセス制限
  },

  // デフォルトのリダイレクト
  { path: '', redirectTo: 'login', pathMatch: 'full' }, // 初回アクセス時はログインページへ
  { path: '**', redirectTo: 'login' } // ★ すべての不正ルートはログインページへ
];
