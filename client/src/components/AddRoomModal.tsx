import { FC, useState } from 'react';
import dayjs from 'dayjs';
import { serverUrl } from '../lib/data';
import { userAtom } from '../lib/user';
import { useAtomValue } from 'jotai';

const fieldsetClass = 'w-96 flex justify-between';
const today = dayjs(new Date());

const AddRoomModal: FC<{
    onClose: () => void;
}> = ({ onClose }) => {
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');
    const [participants, setParticipants] = useState<number[]>([]);

    const day = today.set('hour', +24).set('minute', +60);
    //TODO: add deadline
    const [deadline, setDeadline] = useState(day);
    const user = useAtomValue(userAtom);

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        //TODO: make sure participants does not contain owner
        const res = await fetch(`${serverUrl}/api/rooms/`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                title,
                description,
                deadline,
                owner: user,
                usersTermsToAdd: [],
                participants: participants.filter((e) => e != +user!),
            }),
        });

        if (!res.ok) {
            console.error(await res.text());
        } else {
            console.log(await res.json());
        }

        onClose();
    };
    return (
        <div
            className="absolute flex h-screen w-screen cursor-pointer items-center justify-center bg-stone-800 bg-opacity-95"
            onClick={onClose}
        >
            <div
                className="flex h-1/2 w-1/2 cursor-auto flex-col items-center justify-center rounded-xl bg-stone-100 px-4 py-8"
                onClick={(e) => e.stopPropagation()}
            >
                <h1 className="text-4xl font-bold">Create Room</h1>
                <form
                    onSubmit={handleSubmit}
                    className="flex flex-1 flex-col items-center justify-center gap-4"
                >
                    <fieldset className={`${fieldsetClass}`}>
                        <label>Title: </label>
                        <input
                            type="text"
                            value={title}
                            onChange={(e) => setTitle(e.currentTarget.value)}
                        />
                    </fieldset>
                    <fieldset className={`${fieldsetClass}`}>
                        <label>Description: </label>
                        <input
                            type="text"
                            value={description}
                            onChange={(e) =>
                                setDescription(e.currentTarget.value)
                            }
                        />
                    </fieldset>
                    <fieldset className={`${fieldsetClass}`}>
                        <label>Users to add:</label>
                        <input
                            type="text"
                            onChange={(e) => {
                                setParticipants(
                                    e.currentTarget.value
                                        .split(' ')
                                        .map((id) => parseInt(id))
                                );
                            }}
                        />
                    </fieldset>
                    <button className="w-32 rounded-full bg-emerald-500 px-1 py-2 font-semibold text-white">
                        Create Room
                    </button>
                </form>
            </div>
        </div>
    );
};

export default AddRoomModal;
