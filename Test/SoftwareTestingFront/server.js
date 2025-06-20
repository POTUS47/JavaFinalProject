import express from 'express'
import { exec } from 'child_process'
import path from 'path'
import { fileURLToPath } from 'url'
import { promisify } from 'util'

const execAsync = promisify(exec)
const __dirname = path.dirname(fileURLToPath(import.meta.url))
const app = express()
app.use(express.json())

app.post('/api/run-side-test', async (req, res) => {
  let sidePath = ''
  try {
    if (!req.body || !req.body.testPath) {
      return res.status(400).json({ error: 'testPath 参数缺失' })
    }
    const testPath = req.body.testPath.replace(/^\/+/, '')
    sidePath = path.join(__dirname, 'public', ...testPath.split('/'))
    const { stdout } = await execAsync(`selenium-side-runner \"${sidePath}\"`, {
      timeout: 60000
    })
    res.json({ output: stdout })
  } catch (error) {
    console.error(error)
    res.status(500).json({
      error: error.stderr || error.message,
      path: sidePath
    })
  }
})

app.use(express.static('public'))
app.listen(3000)
