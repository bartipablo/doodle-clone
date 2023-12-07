import { useAtom } from 'jotai';
import { userAtom } from '../lib/user';
import { serverUrl } from '../lib/data';
import { useQuery } from '@tanstack/react-query';

const Home = () => {
    const [user, _] = useAtom(userAtom);
    const query = useQuery({
        queryKey: ['available-rooms'],
        queryFn: async () => {
            return fetch(
                `${serverUrl}/api/rooms/get-participation-rooms/${user}`
            ).then((res) => res.json());
        },
    });
    if (query.isLoading) {
        return <div>Loading...</div>;
    }
    if (query.isError) {
        return <div>Error</div>;
    }
    return <p>{JSON.stringify(query.data)}</p>;
};

export default Home;
