import { useAtom } from 'jotai';
import { userAtom } from '../lib/user';
import { Navigate, useLocation, useSearchParams } from 'react-router-dom';
import React, { useRef } from 'react';
import { Input } from '@/components/ui/input';
import { Button } from '@/components/ui/button';

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
            <Input
                type="text"
                placeholder="Login"
                name="login"
                ref={loginInput}
            />
            <Input
                type="password"
                placeholder="Password"
                name="password"
                ref={passwordInput}
            />
            <Button>Login</Button>
        </form>
    );
};

export default Login;
