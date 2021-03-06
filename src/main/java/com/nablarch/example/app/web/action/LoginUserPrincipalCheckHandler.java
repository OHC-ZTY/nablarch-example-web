package com.nablarch.example.app.web.action;

import java.util.Objects;

import nablarch.common.web.session.SessionUtil;
import nablarch.fw.ExecutionContext;
import nablarch.fw.Handler;
import nablarch.fw.web.HttpRequest;
import nablarch.fw.web.HttpResponse;

/**
 * ログイン状態チェックハンドラ 。
 *
 * @author Nabu Rakutaro
 */
public class LoginUserPrincipalCheckHandler implements Handler<HttpRequest, Object> {

    /**
     * セッションからユーザ情報を取得できなかった場合は、ログイン画面を表示。
     *
     * @param request リクエストデータ
     * @param context 実行コンテキスト
     * @return HTTPレスポンス
     */
    @Override
    public Object handle(HttpRequest request, ExecutionContext context) {
        if (SessionUtil.orNull(context, "userContext") == null
                && !Objects.equals(request.getRequestPath(), "/action/login")) {
            return new HttpResponse("/WEB-INF/view/login/index.jsp");
        }
        return context.handleNext(request);
    }
}
