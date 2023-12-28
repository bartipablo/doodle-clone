import { useQuery } from '@tanstack/react-query';
import { useParams } from 'react-router-dom';
import { serverUrl } from '../lib/data';
import { termSchema } from '../lib/response';
import { useAtomValue } from 'jotai';
import { userAtom } from '../lib/user';
import { useState } from 'react';
import { createPortal } from 'react-dom';
import EditRoomModal from '../components/EditRoomModal';
import AddTermModal from '../components/AddTermModal';
import {
    Card,
    CardContent,
    CardDescription,
    CardHeader,
    CardTitle,
} from '@/components/ui/card';
import { Button } from '@/components/ui/button';
import { CalendarTerm } from '@/components/CalendarTerm';

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
            <Card className="w-3/4 border-0 shadow-none">
                <CardHeader className="flex-row justify-between">
                    <div>
                        <CardTitle className="text-3xl">{title}</CardTitle>
                        <CardDescription className="text-xl">
                            {description}
                        </CardDescription>
                    </div>
                    <div className="flex items-center gap-4">
                        {userId == owner.id && (
                            <>
                                <Button
                                    onClick={() => setEditRoom(true)}
                                    variant="outline"
                                >
                                    Edit room
                                </Button>
                                <Button onClick={() => setAddTerm(true)}>
                                    Add term
                                </Button>
                            </>
                        )}
                        <p>
                            Owner: {userId == owner.id ? `You` : owner.username}
                        </p>
                    </div>
                </CardHeader>
                <CardContent className="flex-1 p-4">
                    {/* TERMS  */}
                    <CalendarTerm terms={terms} />
                </CardContent>
            </Card>
            {editRoom &&
                createPortal(
                    <EditRoomModal
                        description={description}
                        id={+id!}
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
    return (
        <>
            <div className="flex w-3/4 flex-col"></div>
            {editRoom &&
                createPortal(
                    <EditRoomModal
                        description={description}
                        id={+id!}
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
