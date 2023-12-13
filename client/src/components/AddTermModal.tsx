import dayjs from 'dayjs';
import { FC, useRef } from 'react';
import { serverUrl } from '../lib/data';

const today = dayjs(new Date());

const fieldsetClass = 'w-64 flex justify-between';

const AddTermModal: FC<{ id: number; onClose: () => void }> = ({
    id,
    onClose,
}) => {
    const dayRef = useRef<HTMLInputElement>(null);
    const timeRef = useRef<HTMLInputElement>(null);
    const durationRef = useRef<HTMLSelectElement>(null);

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const time = timeRef.current!.value;
        const [hours, minutes, _] = time.split(':');
        const day = dayjs(Date.parse(dayRef.current!.value))
            .set('hour', +hours)
            .set('minute', +minutes);
        const duration = +durationRef.current!.value;

        const res = await fetch(`${serverUrl}/api/terms/`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                startDateTime: day.toISOString(),
                duration,
            }),
        });
        console.log(res.ok);
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
                <h1 className="text-4xl font-bold">Add Term</h1>
                <form
                    className="flex flex-1 flex-col items-center justify-center gap-4"
                    onSubmit={handleSubmit}
                >
                    <fieldset className={`${fieldsetClass}`}>
                        <span>Start date: </span>
                        <input
                            type="date"
                            min={today.format('YYYY-MM-DD')}
                            defaultValue={today.format('YYYY-MM-DD')}
                            ref={dayRef}
                        />
                    </fieldset>
                    <fieldset className={`${fieldsetClass}`}>
                        <span>Start time: </span>
                        <input
                            type="time"
                            defaultValue="16:00"
                            step={`${15 * 60}`}
                            ref={timeRef}
                        />
                    </fieldset>
                    <fieldset className={`${fieldsetClass}`}>
                        <span>Duration: </span>
                        <select ref={durationRef}>
                            <option value="15">15</option>
                            <option value="30">30</option>
                            <option value="45">45</option>
                            <option value="60">60</option>
                        </select>
                    </fieldset>
                    <button className="w-32 rounded-full bg-emerald-500 px-1 py-2 font-semibold text-white">
                        Add term
                    </button>
                </form>
            </div>
        </div>
    );
};

export default AddTermModal;
