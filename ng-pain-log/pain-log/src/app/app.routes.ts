import { Routes } from '@angular/router';
import { LoginComponent } from './features/pages/login/login.component';

export const routes: Routes = [
  // { path: 'login', component: LoginComponent},
  {
    path: 'pages',
    loadChildren: () =>
      import('./features/pages/pages.routes').then((m) => m.default),
  },
  { path: '', redirectTo: 'pages/dashboard', pathMatch: 'full' },
  { path: '**', redirectTo: 'pages/dashboard' },
];
