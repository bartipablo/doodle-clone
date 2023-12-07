import { useAtom } from 'jotai';
import { userAtom } from '../lib/user';
import { Navigate } from 'react-router-dom';

const inputStyle = 'border border-gray-300 rounded-full px-4 py-2';

const Login = () => {
    const [user, setUser] = useAtom(userAtom);
    if (user != undefined) {
        return <Navigate to="/" replace />;
    }
    return (
        <form className="flex flex-col gap-2 rounded">
            <input type="text" className={inputStyle} placeholder="Login" />
            <input
                type="password"
                className={inputStyle}
                placeholder="Password"
            />
            <button
                className="mt-2 rounded-full bg-emerald-500 py-2 text-white"
                type="button"
                onClick={() => {
                    setUser('123');
                }}
            >
                Login
            </button>
        </form>
    );
};

export default Login;
