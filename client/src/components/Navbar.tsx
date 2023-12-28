import { useAtom } from 'jotai';
import { userAtom } from '../lib/user';
import { Link } from 'react-router-dom';
import { ModeToggle } from './mode-toggle';
import { Button, buttonVariants } from './ui/button';

const Navbar = () => {
    const [user, setUser] = useAtom(userAtom);
    return (
        <nav className="flex items-center justify-between px-6 py-4">
            <span className="text-xl font-semibold">Doodle</span>
            <ul className="flex items-center gap-6 font-medium">
                <li>
                    <Link
                        to="/"
                        className={buttonVariants({ variant: 'link' })}
                    >
                        Home
                    </Link>
                </li>
                {user == undefined && (
                    <li>
                        <a href="/login">Login</a>
                    </li>
                )}
                {user != undefined && (
                    <li>
                        <Button
                            variant="outline"
                            onClick={() => {
                                setUser(undefined);
                            }}
                        >
                            Logout
                        </Button>
                    </li>
                )}
                <li>
                    <ModeToggle />
                </li>
            </ul>
        </nav>
    );
};

export default Navbar;
