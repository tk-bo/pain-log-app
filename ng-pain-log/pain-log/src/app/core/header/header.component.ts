import { Component } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { AuthenticationService } from '../../infrastructures/services/authentication.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [MatToolbarModule, MatButtonModule, MatIconModule, CommonModule],
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.less']
})
export class HeaderComponent {
  username: string | null = null;

  constructor(
    private authService: AuthenticationService,
    private router: Router
  ) {
    // コンポーネント生成時にログイン済みならユーザー名を取得
    if (this.authService.isLoggedIn()) {
      this.username = this.authService.getCurrentUsername();
    }
  }

  // ログイン状態の判定
  isLoggedIn(): boolean {
    return this.authService.isLoggedIn();
  }

  // ログアウト処理
  logout(): void {
    this.authService.logout();
    this.username = null;
    this.router.navigate(['/login']);
  }
}
