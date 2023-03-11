| Realisation            | Count (10 runners, 100 updates) | Runtime (ms) |
|------------------------|---------------------------------|--------------|
| Lost-update            | 100 +- 10                       | 1000 +- 200  |
| In-place-update        | 1000                            | 1000 +- 200  |
| Row-level locking      | 1000                            | 2400 +- 200  |
| Optimistic concurrency | 1000                            | 8000 +- 500  |