import { FC } from 'react';
import { type Term } from './CalendarTerm';
import { CardDescription, CardTitle } from './ui/card';

const CalendarWeek: FC<{ terms: Term[] }> = ({ terms }) => {
    if (terms.length === 0) {
        return (
            <div
                className="grid grid-cols-7"
                style={{
                    gridTemplateRows: `repeat(2, minmax(0, 1fr)`,
                }}
            >
                <div className="col-span-1"></div>
                <div className="col-span-6 text-center">No terms</div>
            </div>
        );
    }
    const monday = terms[0].startDateTime.startOf('week').add(1, 'day');

    const minHour = Math.max(
        terms.reduce(
            (acc, cur) => Math.min(acc, cur.startDateTime.get('hour')),
            24
        ) - 1,
        0
    );
    const maxHour = Math.min(
        terms.reduce(
            (acc, cur) => Math.max(acc, cur.startDateTime.get('hour')),
            0
        ) + 1,
        23
    );

    return (
        <div className="relative h-[60vh] overflow-y-scroll">
            <div
                className="absolute sticky left-0 top-0 z-10 grid w-full bg-white dark:bg-neutral-950"
                style={{
                    gridTemplateColumns: `1fr 1fr repeat(7, 3fr)`,
                }}
            >
                <div className="col-span-2 col-start-1 row-start-1 row-end-3"></div>
                {Array.from(Array(7).keys()).map((dayDiff) => {
                    const day = monday.add(dayDiff, 'day');
                    return (
                        <div
                            className="row-start-1 row-end-3 px-2"
                            key={dayDiff}
                        >
                            <CardTitle>{day.format('dddd')}</CardTitle>
                            <CardDescription>
                                {day.format('DD.MM.YYYY')}
                            </CardDescription>
                        </div>
                    );
                })}
            </div>
            <div
                className="grid pt-4"
                style={{
                    gridTemplateColumns: `1fr 1fr repeat(7, 3fr)`,
                    gridTemplateRows: `repeat(${
                        (maxHour + 1 - minHour) * 4
                    }, minmax(0, 3fr)`,
                }}
            >
                {Array.from(Array(maxHour + 1 - minHour).keys()).map(
                    (hourDiff) => {
                        const hour = minHour + hourDiff;
                        return (
                            <CardTitle
                                className="col-start-1 col-end-1 row-span-4 text-2xl"
                                key={hourDiff}
                            >
                                {`${hour}`.padStart(2, '0')}
                            </CardTitle>
                        );
                    }
                )}
                {Array.from(Array((maxHour + 1 - minHour) * 4).keys()).map(
                    (i) => {
                        return (
                            <span
                                className="col-start-2 text-right"
                                style={{ gridRow: 1 + i }}
                                key={i}
                            >
                                {`${(i % 4) * 15}`.padStart(2, '0')}
                            </span>
                        );
                    }
                )}

                {terms.map((term) => {
                    const hour = term.startDateTime.get('hour');
                    let minute = term.startDateTime.get('minute');
                    if (minute < 15) {
                        minute = 0;
                    } else if (minute < 30) {
                        minute = 1;
                    } else if (minute < 45) {
                        minute = 2;
                    } else if (minute < 60) {
                        minute = 3;
                    }
                    const duration = term.duration / 15;
                    const column = term.startDateTime.diff(monday, 'day') + 1;
                    return (
                        <p
                            key={`${hour}${minute}${duration}`}
                            style={{
                                gridRow: 1 + hour * 4 + minute,
                                gridRowEnd: 1 + hour * 4 + minute + duration,
                                gridColumn: column,
                            }}
                            className="mt-[2px] border-2"
                        >
                            lol
                        </p>
                    );
                })}
            </div>
        </div>
    );
};

export default CalendarWeek;
