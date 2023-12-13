import { FC } from 'react';

const EditRoomModal: FC<{ id: number; onClose: () => void }> = ({
    id,
    onClose,
}) => {
    return (
        <div
            className="absolute flex h-screen w-screen cursor-pointer items-center justify-center bg-stone-800 bg-opacity-95"
            onClick={onClose}
        >
            <div
                className="flex h-1/2 w-1/2 cursor-auto flex-col items-center justify-center rounded-xl bg-stone-100"
                onClick={(e) => e.stopPropagation()}
            ></div>
        </div>
    );
};

export default EditRoomModal;
