import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

// バックエンドから受け取るレスポンスのインターフェース
interface LoginResponse {
  token: string;
  // 追加のフィールドがあればここに記載
}

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  // バックエンドの認証APIエンドポイント（環境変数などで管理するのもおすすめ）
  private loginUrl = 'http://localhost:8080/api/auth/login';

  constructor(private http: HttpClient) { }

  /**
   * ログイン処理
   * @param username ユーザー名
   * @param password パスワード
   * @returns JWTトークンを含むレスポンスのObservable
   */
  login(username: string, password: string): Observable<LoginResponse> {
    const payload = { username, password };
    return this.http.post<LoginResponse>(this.loginUrl, payload)
      .pipe(
        // レスポンス受信後、JWTトークンをローカルストレージに保存する
        tap(response => {
          localStorage.setItem('authToken', response.token);
        }),
        // エラー発生時の処理
        catchError(this.handleError)
      );
  }

  /**
   * ログアウト処理：ローカルストレージからJWTトークンを削除
   */
  logout(): void {
    localStorage.removeItem('authToken');
  }

  /**
   * ユーザーのログイン状態を判定する
   * @returns ログイン状態 (true: ログイン済み、false: 未ログイン)
   */
  isLoggedIn(): boolean {
    return !!localStorage.getItem('authToken');
  }

  /**
   * エラーハンドリング
   * @param error HTTPエラーオブジェクト
   * @returns エラーメッセージのObservable
   */
  private handleError(error: HttpErrorResponse) {
    console.error('認証エラー:', error);
    return throwError('ログインに失敗しました。再度お試しください。');
  }
}
