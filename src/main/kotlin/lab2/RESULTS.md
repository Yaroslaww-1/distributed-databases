| Realisation         | Count (10 runners, 100 updates) | Runtime (ms) |
|---------------------|---------------------------------|--------------|
| Without locking     | 150 +- 50                       | 400 +- 100   |
| Optimistic locking  | 1000                            | 2000 +- 200  |
| Pessimistic locking | 1000                            | 3000 +- 200  |
| Atomic long         | 1000                            | 400 +- 100   |