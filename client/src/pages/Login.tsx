import { useAtom } from 'jotai';
import { userAtom } from '../lib/user';
import { Navigate, useLocation, useSearchParams } from 'react-router-dom';
import React, { useRef, useState } from 'react';
import { Input } from '@/components/ui/input';
import { Button } from '@/components/ui/button';
import { serverUrl } from '@/lib/data.ts';

const Login = () => {
    const [user, setUser] = useAtom(userAtom);
    const [searchParams, _] = useSearchParams();
    const [error, setError] = useState<string | null>(null); // Dodajemy stan do przechowywania błędu logowania

    const loginInput = useRef<HTMLInputElement>(null);
    const passwordInput = useRef<HTMLInputElement>(null);
    const [validPassword, setValidPassword] = React.useState(true);
    const [validLogin, setValidLogin] = React.useState(true);

    const submitForm = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        if (loginInput.current == null || passwordInput.current == null) {
            return;
        }
        const loginData = {
            username: loginInput.current.value,
            password: passwordInput.current.value,
        };
        try {
            const response = await fetch(`${serverUrl}/api/auth/login/`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(loginData),
            });

            if (response.ok) {
                response.json().then((data) => {
                    document.cookie = `authenticationToken=${data.authenticationToken}; path=/`;
                    document.cookie = `refreshToken=${data.refreshToken}; path=/`;
                    setUser(data.id);
                });
                // Registration successful
            } else {
                // Registration failed
                const errorMessage = await response.text();
                setError(errorMessage);
            }
        } catch (error) {
            setError('Error: connection refused');
        }
    };

    if (user != undefined) {
        const from = searchParams.get('from');
        if (from != null) {
            return <Navigate to={from} replace />;
        }
        return <Navigate to="/" replace />;
    }

    return (
        <div>
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
            {error && (
                <div className="rounded bg-red-100 p-2 text-red-500">
                    {error}
                </div>
            )}
        </div>
    );
};

export default Login;
