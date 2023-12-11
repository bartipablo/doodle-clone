import { useAtom } from 'jotai';
import { userAtom } from '../lib/user';
import { Navigate, useLocation, useSearchParams } from 'react-router-dom';
import React, { useRef } from 'react';

const inputStyle = 'border border-gray-300 rounded-full px-4 py-2';

const Login = () => {
    const [user, setUser] = useAtom(userAtom);
    const [searchParams, _] = useSearchParams();

    const loginInput = useRef<HTMLInputElement>(null);
    const passwordInput = useRef<HTMLInputElement>(null);
    const [validPassword, setValidPassword] = React.useState(true);
    const [validLogin, setValidLogin] = React.useState(true);

    const submitForm = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        if (loginInput.current == null || passwordInput.current == null) {
            return;
        }
        setUser(loginInput.current.value);
    };

    if (user != undefined) {
        const from = searchParams.get('from');
        if (from != null) {
            return <Navigate to={from} replace />;
        }
        return <Navigate to="/" replace />;
    }

    return (
        <form className="flex flex-col gap-2 rounded" onSubmit={submitForm}>
            <input
                type="text"
                className={inputStyle}
                placeholder="Login"
                name="login"
                ref={loginInput}
            />
            <input
                type="password"
                className={inputStyle}
                placeholder="Password"
                name="password"
                ref={passwordInput}
            />
            <button className="mt-2 rounded-full bg-emerald-500 py-2 text-white">
                Login
            </button>
        </form>
    );
};

export default Login;
