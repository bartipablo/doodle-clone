import { useQuery } from '@tanstack/react-query';
import { useParams } from 'react-router-dom';
import { serverUrl } from '../lib/data';
import Term from '../components/Term';
import { termSchema } from '../lib/response';
import { useAtomValue } from 'jotai';
import { userAtom } from '../lib/user';
import { useState } from 'react';
import { createPortal } from 'react-dom';
import EditRoomModal from '../components/EditRoomModal';
import AddTermModal from '../components/AddTermModal';

const Room = () => {
    const { id } = useParams();
    const userId = useAtomValue(userAtom);

    const [editRoom, setEditRoom] = useState(false);
    const [addTerm, setAddTerm] = useState(false);

    const roomQ = useQuery({
        queryKey: ['room'],
        queryFn: async () => {
            const res = await fetch(`${serverUrl}/api/rooms/get-room/${id}`);
            if (!res.ok) throw new Error('Network response was not ok');
            return await res.json();
        },
    });

    const roomInfoQ = useQuery({
        queryKey: ['room-info'],
        queryFn: async () => {
            const res = await fetch(
                `${serverUrl}/api/rooms/get-room-info/${id}`
            );
            if (!res.ok) throw new Error('Network response was not ok');
            return await res.json();
        },
    });

    if (roomQ.isLoading || roomInfoQ.isLoading) return <p>Loading...</p>;
    if (roomQ.error || roomInfoQ.error) return <p>Error :(</p>;

    const { title, description, terms: terms_, owner } = roomQ.data;
    const { allVotes } = roomInfoQ.data;
    const terms = terms_.map((term: any) => {
        const votes = allVotes
            .filter((v: any) => v.term.id === term.id)
            .map((v: any) => v.voteType);
        term = { ...term, votes };
        const tmp = termSchema.safeParse(term);
        if (tmp.success) {
            return tmp.data;
        }
        throw tmp.error;
    });
    return (
        <>
            <div className="flex w-3/4 flex-col">
                <div className="flex items-center justify-between">
                    <div>
                        <h1 className="text-4xl font-semibold">{title}</h1>
                        <p className="mt-1 text-2xl font-normal text-slate-800">
                            {description}
                        </p>
                    </div>
                    <div className="flex items-center gap-4">
                        {userId == owner.id && (
                            <>
                                <button
                                    className="rounded-full border-2 border-black px-3 py-1 font-semibold"
                                    onClick={() => setEditRoom(true)}
                                >
                                    Edit room
                                </button>
                                <button
                                    className="rounded-full bg-emerald-500 px-3 py-1 font-semibold text-white"
                                    onClick={() => setAddTerm(true)}
                                >
                                    Add term
                                </button>
                            </>
                        )}
                        <p>
                            Owner: {userId == owner.id ? `You` : owner.username}
                        </p>
                    </div>
                </div>
                <div className="grid flex-1 grid-cols-5 grid-rows-3 gap-6 p-4">
                    {/* TERMS  */}
                    {terms.map((term: any) => (
                        <Term key={term.id} term={term} />
                    ))}
                </div>
            </div>
            {editRoom &&
                createPortal(
                    <EditRoomModal
                        description={description}
                        title={title}
                        onClose={() => setEditRoom(false)}
                    />,
                    document.querySelector('#modal') as HTMLElement
                )}
            {addTerm &&
                createPortal(
                    <AddTermModal
                        id={+id!}
                        onClose={() => setAddTerm(false)}
                    />,
                    document.querySelector('#modal') as HTMLElement
                )}
        </>
    );
};

export default Room;
