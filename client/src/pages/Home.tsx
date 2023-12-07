import { useAtom } from 'jotai';
import { userAtom } from '../lib/user';

const Home = () => {
    const [user, _] = useAtom(userAtom);
    return <p>Hello, {user}</p>;
};

export default Home;
