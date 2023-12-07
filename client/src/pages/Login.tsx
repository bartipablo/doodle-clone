import { useAtom } from 'jotai';
import { userAtom } from '../lib/user';

const Login = () => {
    const [_, setUser] = useAtom(userAtom);
    return (
        <div>
            <form>
                <input type="text" />
                <input type="password" />
                <button
                    onClick={() => {
                        setUser('123');
                    }}
                >
                    Login
                </button>
            </form>
        </div>
    );
};

export default Login;
