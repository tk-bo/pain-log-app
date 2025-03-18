import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { LoginResponse } from '../../shared/models/login-response';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  // バックエンドの認証APIエンドポイント
    private loginUrl = 'http://localhost:8081/api/auth/login';
  
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
            localStorage.setItem('username', response.username);
          }),
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
     * 現在のユーザー名を取得する
     * JWTのペイロード（Base64デコード後のJSON）から 'sub' をユーザー名として取得する
     * @returns ユーザー名、またはトークンが無効な場合は null
     */
    getCurrentUsername(): string | null {
      const token = localStorage.getItem('authToken');
      if (!token) {
        return null;
      }
      try {
        const payloadBase64 = token.split('.')[1];
        const payload = JSON.parse(window.atob(payloadBase64));
        return payload.sub || null;
      } catch (e) {
        console.error('トークンの解析に失敗しました', e);
        return null;
      }
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
