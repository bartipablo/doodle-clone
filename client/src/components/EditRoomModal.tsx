import { FC, useState } from 'react';
import { serverUrl } from '../lib/data';

const fieldsetClass = 'w-96 flex justify-between';

const EditRoomModal: FC<{
    title: string;
    description: string;
    id: number;
    onClose: () => void;
}> = ({ title: title_, description: description_, onClose, id }) => {
    const [title, setTitle] = useState(title_);
    const [description, setDescription] = useState(description_);

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        let res = await fetch(`${serverUrl}/api/rooms`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                id,
                title,
                description,
            }),
        });

        if (res.ok) {
            console.log(await res.json());
        } else {
            console.log(await res.text());
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
                <h1 className="text-4xl font-bold">Edit Room</h1>
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
                    <button className="w-32 rounded-full bg-emerald-500 px-1 py-2 font-semibold text-white">
                        Submit changes
                    </button>
                </form>
            </div>
        </div>
    );
};

export default EditRoomModal;
