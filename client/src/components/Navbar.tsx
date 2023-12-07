import { useAtom } from 'jotai';
import { userAtom } from '../lib/user';

const Navbar = () => {
    const [user, setUser] = useAtom(userAtom);
    return (
        <nav className="flex justify-between px-6 py-4">
            <span className="text-xl font-semibold">Doodle</span>
            <ul className="flex gap-6">
                <li>
                    <a href="/">Home</a>
                </li>
                {user == undefined && (
                    <li>
                        <a href="/login">Login</a>
                    </li>
                )}
                {user != undefined && (
                    <li>
                        <button
                            onClick={() => {
                                setUser(undefined);
                            }}
                        >
                            Logout
                        </button>
                    </li>
                )}
            </ul>
        </nav>
    );
};

export default Navbar;
