import { useQuery } from '@tanstack/react-query';
import { useParams } from 'react-router-dom';
import { serverUrl } from '../lib/data';
import Term from '../components/Term';

const Room = () => {
    const { id } = useParams();

    const { isLoading, error, data } = useQuery({
        queryKey: ['room'],
        queryFn: async () => {
            const res = await fetch(`${serverUrl}/api/rooms/get-room/${id}`);
            if (!res.ok) throw new Error('Network response was not ok');
            return await res.json();
        },
    });

    if (isLoading) return <p>Loading...</p>;
    if (error) return <p>Error :(</p>;

    const { title, description, terms, owner } = data;
    return (
        <div className="flex w-3/4 flex-col border-2">
            <div className="flex items-center justify-between">
                <div>
                    <h1 className="text-4xl font-semibold">{title}</h1>
                    <p className="mt-1 text-2xl font-normal text-slate-800">
                        {description}
                    </p>
                </div>
                <p>Owner: {owner.username}</p>
            </div>
            <div className="flex-1 bg-red-200">
                {/* TERMS  */}
                {terms.map((_term: any) => (
                    <Term />
                ))}
            </div>
        </div>
    );
};

export default Room;
