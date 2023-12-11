import { useAtomValue } from 'jotai';
import { userAtom } from '../lib/user';
import { serverUrl } from '../lib/data';
import { useQuery } from '@tanstack/react-query';
import RoomThumbnail from '../components/RoomThumbnail';
import { useEffect, useState } from 'react';

const Home = () => {
    const user = useAtomValue(userAtom);
    const [data, setData] = useState<any[]>([]);

    // TODO: Fix types
    const queryRooms = async ({ queryKey }: { queryKey: any }) => {
        const res = await fetch(`${serverUrl}/api/rooms/${queryKey}/${user}`);
        if (!res.ok) throw new Error('Network response was not ok');

        return res.json();
    };

    const queryOne = useQuery({
        queryKey: ['get-participation-rooms'],
        queryFn: queryRooms,
    });

    const queryTwo = useQuery({
        queryKey: ['get-owned-rooms'],
        queryFn: queryRooms,
    });

    useEffect(() => {
        if (queryOne.data && queryTwo.data) {
            setData([...queryOne.data, ...queryTwo.data]);
        }
    }, [queryOne.data, queryTwo.data]);

    if (queryOne.isLoading || queryTwo.isLoading) return <p>Loading...</p>;
    if (queryOne.error || queryTwo.error) return <p>Error :(</p>;

    return (
        <div className="w-1/2">
            <p className="mb-4 text-3xl font-bold">Your rooms:</p>
            <ul>
                {data.map((room: any) => (
                    <RoomThumbnail room={room} key={room.id} />
                ))}
            </ul>
        </div>
    );
};

export default Home;
